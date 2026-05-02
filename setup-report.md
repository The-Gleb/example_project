# Setup Report — Tiny Clock

Project type: `android_only`
Workdir: `/workspace/projects/tiny-clock`
Repository: `https://github.com/The-Gleb/example_project`

## What changed

### CI/CD workflows
- Updated `.github/workflows/android-preview.yml`:
  - Hardened the Firebase distribution gate. The previous condition
    `if: ${{ env.FIREBASE_APP_ID != '' }}` could not see the secret because
    `env` is only populated for the step body, not for the `if:` expression
    evaluator. Replaced with `if: ${{ secrets.FIREBASE_APP_ID != '' }}` so
    the step is correctly skipped when the secret is unset and runs when
    it is configured.
  - Workflow runs on `pull_request` and `workflow_dispatch`, executes the
    full Android quality gate (`./gradlew testDebugUnitTest lintDebug
    assembleDebug`), uploads the debug APK as an artifact, and (when
    `FIREBASE_APP_ID` is set) distributes via Firebase App Distribution.
- Removed `.github/workflows/backend-deploy.yml` and
  `.github/workflows/backend-preview.yml`. They are not part of the
  `android_only` skeleton — there is no `backend/` directory, so they would
  never run and would only confuse future contributors. They can be
  re-introduced if the project type is later changed to `android_go_backend`.

### Skeleton fix (minimum needed for the gate to be invokable)
- `app/src/main/res/values/themes.xml` and `app/src/main/AndroidManifest.xml`:
  renamed the application theme from `Theme.tiny-clock` to
  `Theme.TinyClock`. Hyphens are not valid characters in Android resource
  names, so the previous skeleton failed at `mergeDebugResources` before
  anything else could run. The rename is a non-functional skeleton fix to
  let the gate proceed.

## Quality-gate verification

Command run from the workdir:

```
./gradlew testDebugUnitTest lintDebug assembleDebug
```

Outcome: **invoked successfully, build currently fails** at
`processDebugResources` with:

```
ERROR: AAPT: error: resource style/Theme.Material3.DayNight.NoActionBar
not found.
```

This is an app-code/dependency gap in the seeded skeleton — `app/build.gradle.kts`
declares only the Compose Material3 dependency, while `themes.xml` references
the AppCompat-style Material3 theme. Resolving it requires either adding
`com.google.android.material:material` and fixing the theme parent, or
switching to a non-Material3-XML theme. That is application work and is
deliberately left for the first `development_run` task — project_setup is
not allowed to implement application features.

## Manual steps remaining for the operator

1. Configure the GitHub Actions secrets listed in `secrets-checklist.md`
   in the GitHub repository settings. The Firebase distribution step is
   automatically skipped when `FIREBASE_APP_ID` is empty, so the workflow
   will continue to function for plain build/test PRs even before the
   Firebase secrets are set.
2. Optionally, register the Firebase Android app and add the Firebase
   tester group(s) before enabling distribution.
3. The first `development_run` task should fix the Material3 theme
   dependency so the Android quality gate turns green in CI. Until then,
   PR builds will fail at `processDebugResources`.
