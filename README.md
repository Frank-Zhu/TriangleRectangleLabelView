[![Android Gems](http://www.android-gems.com/badge/Frank-Zhu/TriangleRectangleLabelView.svg?branch=master)](http://www.android-gems.com/lib/Frank-Zhu/TriangleRectangleLabelView)

TriangleRectangleLabelView
==========================

A label view. Extends TextView
<br>
![](https://raw.githubusercontent.com/Frank-Zhu/TriangleRectangleLabelView/master/art/art.png)

Usage
============
Also, you can optionally set the following attributes in your layout xml file to customize the behavior
of the TriangleRectangleLabelView.

 * `trlvBgColor` (defaults to 0xff41c7cd)
 The label view background

 * `trlvCircleColor` (defaults to 0xffffffff)
 The label view Circle background

 * `trlvLineColor` (defaults to 0xfffb9ece)
 The label view dividing line background

 more see this file:
```xml
	<declare-styleable name="TriangleRectangleLabelView">
        <attr name="trlvIsLeft" format="boolean" />
        <attr name="trlvIsShowCircle" format="boolean" />
        <attr name="trlvIsShowLine" format="boolean" />
        <attr name="trlvIsDrawRoundRect" format="boolean" />
        <attr name="trlvBgColor" format="color" />
        <attr name="trlvLineColor" format="color" />
        <attr name="trlvCircleColor" format="color" />
        <attr name="trlvCircleSpaceRectangle" format="dimension" />
        <attr name="trlvCircleRadius" format="dimension" />
        <attr name="trlvLineWidth" format="dimension" />
        <attr name="trlvRoundRectWidth" format="dimension" />
        <attr name="trlvRoundRectRadius" format="dimension" />
    </declare-styleable>
```

The typical usage is to declare the widget directly into the layout XML file. For example:
```xml
	<com.ecloud.trianglerectanglelabelview.TriangleRectangleLabelView
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="#fff"
        android:singleLine="true"
        android:text="Label 1"
        android:layout_below="@id/trv_name"
        android:textSize="16sp"
        android:layout_toEndOf="@id/trv_name"
        android:layout_toRightOf="@id/trv_name"
        app:trlvIsLeft="true"
        app:trlvCircleRadius="4dp"
        app:trlvBgColor="#ff000000"
        app:trlvCircleColor="#ffffff"
        app:trlvRoundRectRadius="12dp"
        app:trlvRoundRectWidth="10dp" />
```


Using this library?
============
If you're using this library in one of your projects just [send me a G+ message](https://plus.google.com/u/0/108962319538026346008/posts/p/pub) and I'll add your project to the list.

Contribution
============
### Pull requests are welcome!

If you have a bug to report a feature to request or have other questions, [file an issue](https://github.com/Frank-Zhu/TriangleRectangleLabelView/issues). I'll try to answer as soon as I can.

About me
============
[Click Me](http://frank-zhu.github.io/about.html)

License
============

    Copyright 2014 Frank Zhu

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
