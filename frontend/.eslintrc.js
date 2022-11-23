module.exports = {
    "env": {
        "browser": true,
        "es2021": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:react/recommended",
        "plugin:@typescript-eslint/recommended"
    ],
    "overrides": [],
    "ignorePatterns": ['*.test.tsx', 'reportWebVitals.js', 'setupTests.js'],
    "parser": "@typescript-eslint/parser",
    "parserOptions": {
        "ecmaVersion": "latest",
        "sourceType": "module"
    },
    "plugins": [
        "react",
        "@typescript-eslint"
    ],
    "settings": {
        "react": {
            "version": "detect"
        }
    },
    "rules": {
        "object-shorthand": [2, "always", {
            "ignoreConstructors": false,
            "avoidQuotes": false,
        }],
        "max-len": [2, 120, {
            "ignoreStrings": true,
            "ignoreTemplateLiterals": true,
            "ignoreComments": true,
        }],
        "consistent-return": 0,

        "prefer-destructuring": [2, { "array": false, "object": false }, { "enforceForRenamedProperties": false }],
        "prefer-object-spread": 0,
        "prefer-rest-params": 0,
        "prefer-spread": 0,
        "function-call-argument-newline": 0,
        "function-paren-newline": 0,
        "no-plusplus": [2, {"allowForLoopAfterthoughts": true}],
        "no-param-reassign": 1,
        "strict": [2, "safe"],
    }
}
