# Secrets Checklist — Example Project 4

The CI/CD configuration is `android_only`. Configure the secrets below
in **GitHub → Settings → Secrets and variables → Actions** for the
repository (or for the relevant environment) before relying on Firebase
App Distribution from the preview workflow.

Without these secrets, `.github/workflows/android-preview.yml` still
runs the quality gate and uploads a debug APK as a workflow artifact —
the Firebase distribution step is gated and is silently skipped when
`FIREBASE_APP_ID` is empty.

## Required for Firebase App Distribution

| Secret | Required? | Purpose |
| --- | --- | --- |
| `FIREBASE_APP_ID` | Yes (to enable distribution) | Firebase App ID for the Android app, e.g. `1:1234567890:android:abcdef`. The presence of this secret is also the gate that turns the distribute step on. |
| `FIREBASE_TOKEN` | Yes (when `FIREBASE_APP_ID` is set) | CI token for the Firebase CLI. Generate with `firebase login:ci` from a workstation authenticated to the Firebase project. |
| `FIREBASE_TESTER_GROUPS` | Optional | Comma-separated tester group aliases that should receive the build. Defaults to `internal-testers` when unset. |

## Not required for this project type

The following secrets are **not** used by any workflow in this
repository because `project_type=android_only`. Do not configure them:

- `DEPLOY_HOST`, `DEPLOY_USER`, `DEPLOY_SSH_KEY` — only needed when a
  Go backend deploy workflow is present.
- Container registry credentials (e.g. `GHCR_TOKEN`, `DOCKERHUB_*`).
- Play Store service-account JSON — release upload is not configured by
  the skeleton profile.

## How to verify

1. After configuring `FIREBASE_APP_ID` and `FIREBASE_TOKEN`, open a PR
   or trigger `Android Preview` via **Actions → Run workflow**.
2. Confirm the `Distribute via Firebase App Distribution` step runs
   (instead of being skipped) and that testers receive the build.
3. If the step is skipped unexpectedly, double-check that
   `FIREBASE_APP_ID` is set at the repository (not environment) level,
   or scope the workflow to the matching environment.
