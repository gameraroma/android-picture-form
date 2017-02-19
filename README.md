# android-picture-form-example
This is example to build form with picture in Android app. This app was wrote in Kotlin. Follow these topic to build form using Kotlin.

## Introduce to App
The form app look like [this](https://drive.google.com/file/d/0B9Cpm2ZSR1FMdGtPN3dnMmtxdVk/view)

## Configure Kotlin
You have to follow the step as links below
* [Getting started with Android and Kotlin](https://kotlinlang.org/docs/tutorials/kotlin-android.html) - for config Kotlin at create project.
* [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html) - for avoid using `findViewById`
If you succeed to config Kotlin, let get start!

## Sanity Check
Make sure that you have `MainActivity.kt` and `activity_main.xml`. Your `MainActicity.kt` should have this:

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

".xml file is still the same, since Kotlin have no effect with them.

## Building Your UI

This app use few widget to build the form.

### To
