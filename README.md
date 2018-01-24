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
#### If `Async` options is set to `true` then blurring will be done asyncronously.
#### Set `intensity` to control the blurring. `MAX_RADIUS` is 25.

## Screenshot:
![Screenshot](https://github.com/anuranBarman/BlurIt/blob/master/screenshot.png "Blurred Background")
