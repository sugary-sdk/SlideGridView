# SlideGridView


## Customization

### Some Attributes

#### Segmented Button
| Option Name      				| Format                 | Description                              |
| ---------------- 				| ---------------------- | -----------------------------            |
| app:row         | `integer`               |  number of rows    |
| app:column       | `integer`               | number of colums |



#### Examples

##### In Xml Layout
```xml
<in.sugary.slidegridview.SlideGridView
     android:id="@+id/gridView"
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     android:listSelector="@android:color/transparent"
     app:column="3"
     app:row="4"/>
```

##### In Java
###### in your main activity:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    gridView = (SlideGridView) findViewById(R.id.gridView);
    gridView.setSlideGridAdapter(new Adapter(this));
        
    // you can change grid view size any time. don't forget calling update method.
    changeSize(4, 4);
}
        
private void changeSize(int r, int c) {
     gridView.setRow(r);
     gridView.setColumn(c);
     gridView.update();
}
```

###### in your adapter:
```java
// In adapter, you can define your grid size by passing it to super method.
int dataSize = 15;
Adapter(Context context) {
        super(context, R.layout.grid_item, dataSize);
        this.context = context;
}
```

## License

This project is licensed under the Apache License Version 2.0 - see the [LICENSE.md](LICENSE.md) file for details
