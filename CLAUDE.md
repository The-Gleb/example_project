# Example Project 3

## Working Agreement
- Work only inside `/workspace/projects/example-project-3`. Never touch files outside it.
- Treat the platform backend as the outer orchestrator and yourself as the inner orchestrator for one task at a time.
- Prefer editing existing files over introducing new frameworks.
- Keep changes small, intentional, and reviewable.
- Do not push, deploy, or release anything unless the outer orchestrator explicitly asks for it as part of the current task.

## Repo metadata
- Repository: `https://github.com/The-Gleb/example_project`
- Default branch: `main`
- Project type: `android_only`
- Skeleton profile: `android-only`

## Subagents

Use these only when they materially help. Pick the smallest set; do not call all of them.

- `engineering-software-architect` — shape an unclear approach before coding.
- `engineering-mobile-app-builder` — Android implementation.
- `engineering-backend-architect` — Go backend implementation (only when `project_type=android_go_backend`).
- `engineering-code-reviewer` — review the local diff before declaring success.
- `engineering-devops-automator` — only when CI/CD or deploy files are in scope.

## Development Run Protocol

Inside one Claude run, execute the full delivery loop:

1. Read the task goal, context, constraints, relevant files, acceptance criteria, and the retry budget.
2. Produce a short implementation plan.
3. Implement the change in the project workdir.
4. Run the relevant local quality gate (see below).
5. Have the reviewer subagent inspect the local diff.
6. If testing or review fails, repair and repeat from step 3 — but never exceed the retry budget.
7. If code changed and the gate is green, create a commit (Conventional Commits format).
8. If `origin` has push credentials, push the working branch and open a PR. Otherwise, leave the local commit and report `pr: null`.
9. Return one final strict JSON result.

A `development_run` is only complete when planning, implementation, testing, and review are all finished — or the retry budget is exhausted with a `fail` status.

## Quality Gates

- Android: `./gradlew testDebugUnitTest lintDebug assembleDebug`
- Backend (only when `backend/` exists): `cd backend && go test ./...`

If a gate command is not applicable to the change, still run it; the platform validates that you actually invoked it.

## Forbidden actions

- Do not edit anything under `.git/`.
- Do not force-push (`git push -f`, `--force-with-lease`, etc.).
- Do not delete branches you didn't create.
- Do not skip pre-commit, lint, or test hooks (no `--no-verify`).
- Do not deploy to production. The platform handles release explicitly via a separate task kind.
- Do not write secrets to the repo. Reference them by name in `secrets-checklist.md` instead.
- Do not edit `CLAUDE.md` itself unless the task explicitly says so.

## Final JSON Contract

Return valid JSON only — no markdown, no prose outside the object.

```json
{
  "status": "pass|fail",
  "summary": "...",
  "plan": ["..."],
  "changes": [
    { "file": "...", "action": "created|updated|deleted", "details": "..." }
  ],
  "testing": {
    "status": "pass|fail",
    "commands": [
      { "command": "...", "status": "pass|fail", "details": "..." }
    ]
  },
  "review": {
    "status": "pass|fail",
    "findings": [
      { "severity": "high|medium|low", "file": "...", "issue": "...", "suggestion": "..." }
    ]
  },
  "commit": { "sha": "...", "message": "..." },
  "pr": null,
  "attempts_used": 1,
  "next_action": "done|needs_followup"
}
```

`commit` is null when no code changes were warranted. `pr` is `{"url": "..."}` when a PR was opened, otherwise `null`. `attempts_used` must not exceed the task's `retry_limit`.
