# Setup Report — Example Project 4

Project type: `android_only`
Workdir: `/workspace/projects/example-project-4`
Default branch: `main`

## What this run did

1. Inspected the repository skeleton at the workdir.
2. Ran the local Android quality gate end-to-end:
   `./gradlew testDebugUnitTest lintDebug assembleDebug` — **PASS**.
3. Repaired three small skeleton defects that prevented the gate from
   running on a fresh checkout (see "Skeleton fixes" below).
4. Cleaned up the `.github/workflows/` directory so it matches the
   `android_only` project profile.
5. Verified `.github/workflows/android-preview.yml` runs the quality
   gate, uploads the debug APK, and gates Firebase App Distribution on
   the `FIREBASE_APP_ID` secret.
6. Wrote `secrets-checklist.md` listing every GitHub Actions secret the
   operator must configure for previews to actually distribute.

## CI/CD layout (final)

```
.github/workflows/
└── android-preview.yml   # PR + workflow_dispatch
```

`android-preview.yml`:
- Triggers on `pull_request` and `workflow_dispatch`.
- Sets up Temurin JDK 21, Android SDK, and Gradle caching.
- Runs `./gradlew testDebugUnitTest lintDebug assembleDebug`.
- Uploads `app/build/outputs/apk/debug/*.apk` as the
  `example-project-4-debug-apk` artifact.
- Distributes the debug APK via Firebase App Distribution **only when
  the `FIREBASE_APP_ID` secret is configured**. With the secret unset,
  the distribute step is skipped and the workflow still passes — so PRs
  do not fail just because Firebase is not wired up yet.

No `backend-*.yml` workflow is needed because `project_type=android_only`.
The previously-present `backend-deploy.yml` and `backend-preview.yml`
files were deleted to avoid noisy failures referencing a nonexistent
`backend/` directory.

## Skeleton fixes applied during setup

These were necessary so the quality gate (and therefore the CI workflow)
actually completes on a fresh checkout. They are configuration-level,
not application features:

- `app/src/main/res/values/themes.xml` — renamed style from
  `Theme.example-project-4` to `Theme.ExampleProject4`. Android resource
  names cannot contain hyphens, so the original name failed
  `mergeDebugResources`.
- `app/src/main/AndroidManifest.xml` — updated `android:theme` to match
  the renamed style and removed `android:icon` /
  `android:roundIcon` references to `@mipmap/ic_launcher*` since no
  launcher icon resources ship in the skeleton. The default OS launcher
  icon will be used until real icons are added.
- `app/build.gradle.kts` — added
  `com.google.android.material:material:1.12.0`, which provides the
  `Theme.Material3.*` XML parents the manifest theme inherits from.

## Manual steps remaining for the operator

1. Configure GitHub Actions secrets — see `secrets-checklist.md`. Until
   `FIREBASE_APP_ID` and `FIREBASE_TOKEN` are set, the preview workflow
   will build and upload the APK as a CI artifact but will not push to
   Firebase App Distribution.
2. Decide whether to add real launcher icon resources
   (`mipmap/ic_launcher`, `mipmap/ic_launcher_round`) and re-add the
   `android:icon` / `android:roundIcon` attributes.
3. Decide on a release / production distribution path. This setup
   covers debug previews only; signed release builds and Play Store /
   internal-track upload are out of scope for the `android_only`
   skeleton profile.

## Quality gate evidence

Last invocation:

```
$ ./gradlew testDebugUnitTest lintDebug assembleDebug
BUILD SUCCESSFUL
52 actionable tasks: 32 executed, 20 up-to-date
```
