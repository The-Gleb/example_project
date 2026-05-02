# GitHub Actions Secrets Checklist — Example Project 3

The operator must configure the following secrets on the GitHub repository
(Settings → Secrets and variables → Actions) before the corresponding CI
behavior becomes active. The Android preview workflow degrades gracefully when
the Firebase secrets are absent: tests, lint, and the debug build still run,
but the distribution step is skipped.

## Android preview / Firebase App Distribution

Used by `.github/workflows/android-preview.yml`.

| Secret | Required? | Purpose |
| --- | --- | --- |
| `FIREBASE_APP_ID` | Required to enable distribution | Firebase App Distribution app ID (`1:...:android:...`). When unset, the distribution step is skipped and the workflow still uploads the debug APK as a build artifact. |
| `FIREBASE_TOKEN` | Required when `FIREBASE_APP_ID` is set | Firebase CLI auth token used by `firebase appdistribution:distribute`. Generate with `firebase login:ci`. |
| `FIREBASE_TESTER_GROUPS` | Optional | Comma-separated tester group aliases. Defaults to `internal-testers` when unset. |

## Notes

- Do not commit secret values to the repository. Reference them by name only,
  exactly as listed above.
- If the project later adds a Go backend (`project_type=android_go_backend`),
  reintroduce `.github/workflows/backend-deploy.yml` and append the deploy /
  registry credentials it requires (e.g. `DEPLOY_HOST`, `DEPLOY_USER`,
  `DEPLOY_SSH_KEY`) to this checklist at that time.
