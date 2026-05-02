# Secrets Checklist — Tiny Clock

Configure these GitHub Actions secrets in the repository settings
(`Settings → Secrets and variables → Actions → New repository secret`).
Each secret name below is referenced verbatim by
`.github/workflows/android-preview.yml`.

## Firebase App Distribution (optional — gates the distribute step)

| Secret name | Required? | Purpose |
| --- | --- | --- |
| `FIREBASE_APP_ID` | Required to enable distribution | Firebase App ID for the Android app (looks like `1:1234567890:android:abcdef`). When empty, the distribute step is skipped and the workflow still produces an APK artifact. |
| `FIREBASE_TOKEN` | Required when `FIREBASE_APP_ID` is set | CI token from `firebase login:ci`, used to authenticate `firebase appdistribution:distribute`. |
| `FIREBASE_TESTER_GROUPS` | Optional | Comma-separated tester group aliases. Defaults to `internal-testers` when unset. |

## Notes

- Do **not** commit any secret values to the repository. This file lists
  only the names and intent, never the values.
- The Firebase step uses the legacy CI token flow; if you migrate to a
  service account, swap `FIREBASE_TOKEN` for a `GOOGLE_APPLICATION_CREDENTIALS`
  JSON secret and update the workflow accordingly.
- No backend secrets are required for this project (`project_type=android_only`).
  If the project is later promoted to `android_go_backend`, the backend
  deploy workflow will need its own `DEPLOY_HOST`, `DEPLOY_USER`, and
  `DEPLOY_SSH_KEY` secrets — they are intentionally absent from this list.
