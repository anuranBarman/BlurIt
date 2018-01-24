# BlurIt
simple android library to blur background bitmaps or drawables.

## Usage:
1. With Bitmap
```java
BlurIt.with(getApplicationContext()).intensity(25).Async(true).load(R.drawable.background).into((ImageView)findViewById(R.id.image));
```
2. With Drawable Resource
```java
BlurIt.with(getApplicationContext()).intensity(25).Async(true).load(R.drawable.background).into((ImageView)findViewById(R.id.image));
```
##### add this to your project level gradle file
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
##### add this to your app level gradle file
```
    dependencies {
	        compile 'com.github.anuranBarman:BlurIt:0.1.1'
	}
```

##### add this to your app level gradle file
```
defaultConfig {
    ...
    renderscriptTargetApi 18
    renderscriptSupportModeEnabled true
}

```
#### If `Async` options is set to `true` then blurring will be done asyncronously.
#### Set `intensity` to control the blurring. `MAX_RADIUS` is 25.

## Screenshot:
![Screenshot](https://github.com/anuranBarman/BlurIt/blob/master/screenshot.png "Blurred Background")
