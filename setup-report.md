# Setup Report — Example Project 3

## Project profile

- Slug: `example-project-3`
- Project type: `android_only`
- Default branch: `main`
- Repository: https://github.com/The-Gleb/example_project
- Android package: `com.softwarefactory.exampleproject3`

## Deliverables

| File | Status | Notes |
| --- | --- | --- |
| `.github/workflows/android-preview.yml` | present (unchanged) | PR/manual trigger; runs `./gradlew testDebugUnitTest lintDebug assembleDebug`, uploads debug APK, and conditionally distributes via Firebase App Distribution when `FIREBASE_APP_ID` is set. |
| `.github/workflows/backend-deploy.yml` | removed | Not applicable to `android_only` projects. The skeleton shipped a backend deploy workflow; it has been deleted because there is no `backend/` directory and the contract requires this workflow only when `project_type=android_go_backend`. |
| `.github/workflows/backend-preview.yml` | removed | Same reasoning as above. |
| `setup-report.md` | created | This file. |
| `secrets-checklist.md` | created | Lists every GitHub Actions secret the operator must configure. |

## Quality gate verification

Command run: `./gradlew testDebugUnitTest lintDebug assembleDebug`

Result: **fails at configuration** with:

```
Plugin [id: 'org.jetbrains.kotlin.plugin.compose', version: '1.9.24', apply: false] was not found
```

Root cause: the skeleton declares the standalone Kotlin Compose Compiler plugin
(`org.jetbrains.kotlin.plugin.compose`), which was introduced in Kotlin 2.0. The
skeleton pins Kotlin to `1.9.24`, so this plugin coordinate does not exist.

This is an application-level build configuration issue and is out of scope for
the project_setup task (which is restricted to CI/CD). It is documented here so
the first development_run task can resolve it before any feature work — for
example by bumping the Kotlin plugins to 2.0+ in `build.gradle.kts`, or by
dropping the standalone compose plugin and reverting to
`composeOptions { kotlinCompilerExtensionVersion = "..." }` with a Kotlin 1.9.x
compatible compiler.

The Android preview workflow itself is wired correctly; once the build script
is repaired, the same `./gradlew testDebugUnitTest lintDebug assembleDebug`
command will succeed in CI.

## Manual steps remaining

1. Configure the GitHub Actions secrets listed in `secrets-checklist.md` on the
   repository (or organization) used to host this project. The Firebase
   distribution step is gated on `FIREBASE_APP_ID` being set, so the workflow
   will still pass without it but no preview build will be distributed.
2. Resolve the Kotlin/Compose plugin mismatch described above as part of the
   first development_run task, so the Android preview workflow can run green.
3. Ensure `origin` push credentials are available in the runner if you want
   future development_run tasks to push branches and open PRs automatically;
   otherwise they will leave local commits and report `pr: null`.

## What was NOT changed

- No application source under `app/` was modified.
- `CLAUDE.md`, `project.yaml`, and Gradle wrapper files were left untouched.
- No production deploy workflows were introduced; this project type does not
  require them.
