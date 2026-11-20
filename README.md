# CampusLife

**COMPX202/Y05853 – Assignment One: Introduction to Android**
Student ID: **20243007031**

CampusLife is a small Android application that acts as a pocket guide to the
University of Waikato. The home screen shows a banner and four student service
areas – **Facilities**, **Events**, **Clubs** and **Support** – and tapping one of
them opens a detail screen that is filled with the matching text and icon from
`arrays.xml`.

## Requirements covered

| Part | Requirement | Where |
| --- | --- | --- |
| A | Project created with Git, Empty Views Activity, Java, min API 24 | `app/build.gradle`, `.gitignore`, Git history |
| B | Responsive XML layout of the home screen, no hard-coded sizes | `res/layout/activity_main.xml` |
| C | Application name, launcher icon, brand colours, button selector | `strings.xml`, `mipmap-*`, `colors.xml`, `drawable/button_selector.xml` |
| D | Separate landscape layout with the four buttons in one row | `res/layout-land/activity_main.xml` |
| E | `onClick` events with a Toast per button | `MainActivity.java` |
| F | New `DetailActivity` and its XML layout | `DetailActivity.java`, `res/layout/activity_detail.xml` |
| G | Explicit Intent carrying a unique topic identifier | `MainActivity.openTopic()`, `DetailActivity.EXTRA_TOPIC_INDEX` |
| H | Detail screen populated from `arrays.xml`, no `if` / `switch` | `DetailActivity.populateUi()` |
| I | Code quality: structure, naming and comments | all Java and XML files |

## Screens

* **Home (portrait)** – banner over the top half of the screen, 2 × 2 grid of
  green topic buttons over the bottom half.
* **Home (landscape)** – the same banner, the four topic buttons in a single row.
* **Detail** – banner header with the topic icon and short name, then a
  scrollable card with the full title and the content text.

## Project structure

```
.
├── app
│   ├── build.gradle
│   └── src/main
│       ├── AndroidManifest.xml
│       ├── java/com/example/campuslife
│       │   ├── MainActivity.java
│       │   └── DetailActivity.java
│       └── res
│           ├── drawable/button_selector.xml
│           ├── drawable-nodpi/            (banner and topic icons)
│           ├── layout/activity_main.xml
│           ├── layout/activity_detail.xml
│           ├── layout-land/activity_main.xml
│           ├── mipmap-*/                  (launcher icons)
│           └── values/{arrays,colours,dimens,strings,themes}.xml
├── build.gradle
├── gradle/wrapper
├── gradlew / gradlew.bat
├── settings.gradle
└── README.md
```

## Building and running

1. Open the project folder in Android Studio (Giraffe or newer).
2. Android Studio uses the bundled JDK 17 and the Gradle wrapper, so no extra
   configuration is required; `local.properties` is generated automatically by
   the IDE.
3. Select an emulator or a physical device with **Android 7.0 (API 24)** or
   higher and press **Run**.

From the command line:

```bash
./gradlew assembleDebug          # build the APK
./gradlew installDebug          # install it on the connected device
```

The APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

## Implementation notes

**Responsive layouts.** Every width and height in the XML files is either
`match_parent`, `wrap_content`, `0dp` with a `layout_weight` (so the layout
divides the space that is actually available), or a value taken from
`values/dimens.xml`. The only hard-coded text size is the 40sp banner title,
which the specification explicitly allows. Because the banner uses
`scaleType="centerCrop"`, the image fills the space it is given on any screen
size, and because the buttons use weights, the 2 × 2 grid of the portrait
layout and the single row of the landscape layout stretch automatically.

**Button states.** The four `ImageButton`s share
`drawable/button_selector.xml`, a `selector` that paints the button green
(`#1B5E20`) in its normal state and light green (`#66BB6A`) while it is pressed.

**Passing data between activities.** Each click handler in `MainActivity`
shows a Toast and then starts `DetailActivity` with an explicit Intent that
carries the index of the topic as an `int` extra. The index is the unique
identifier of the topic.

**No `if` statements or `switch` cases.** `DetailActivity` reads the index and
uses it directly as a position in the three arrays of `arrays.xml`
(`string_array_titles`, `string_array_content`, `string_array_images`), so one
line of code per value is enough:

```java
String[] titles = getResources().getStringArray(R.array.string_array_titles);
titleView.setText(titles[topicIndex].trim());
```

The icon is resolved by name with `getResources().getIdentifier(...)` because
the names are stored as strings in `arrays.xml`. The short header labels are a
separate `topic_labels` array in `strings.xml`, again indexed by position.

**Content formatting.** The items in `arrays.xml` contain `\n` escapes followed
by the indentation of the XML file. `DetailActivity.formatContent()` converts
the escapes, strips the indentation and trailing spaces of every line and
collapses empty lines, so the text does not show an uneven left margin (see
Figures 6–9). It is implemented with regular expressions only, which keeps
`DetailActivity` free of `if` statements and `switch` cases.

## Images

All images (`img_main_banner.png`, the four `img_btn_*.png` icons and
`img_campusLife_logo.webp`) were supplied on Canvas for this assignment. The
launcher icon is generated from the logo in five densities, together with an
adaptive icon for API 26 and above.

## Git

The repository is developed in nine commits, one per assignment part:

```
Part A - create the Android project
Part B - home screen XML layout
Part C - application resources (name, icon, colours, button selector)
Part D - landscape layout
Part E - onClick events with Toasts
Part F - DetailActivity and its layout
Part G - explicit Intent with the topic identifier
Part H - populate the detail screen from arrays.xml
Part I - code quality, comments and README
```
