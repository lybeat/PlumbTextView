## PlumbTextView

PlumbTextView is a vertical TextView. You can easily define a variety of effects.

## Feature

1. Arrange text vertically.
2. Can set column spacing and kerning of text.
3. Can add a "regex" to split text. PlumbTextView will change the column at the character that the regular expression contains. And characters will not in PlumbTextView.
4. Can add a vertical bar to the left of the text.
5. Can set font style an family for text. 

## Screenshot

![vertical_textview.gif](https://github.com/lybeat/PlumbTextView/blob/master/screenshot/plumb_textview.gif)

## Gradle Dependency

compile 'cc.sayaki.widget:plumb-textview:1.0.1'

## Usage

You can easily use PlumbTextView as TextView. Just set properties in xml. Of course, you can aslo set in Java code.

```
<cc.sayaki.widget.PlumbTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:paddingBottom="30dp"
    android:paddingTop="30dp"
    sayaki:columnSpacing="20dp"
    sayaki:leftLine="true"
    sayaki:leftLineColor="@color/colorAccent"
    sayaki:leftLinePadding="4dp"
    sayaki:letterSpacing="6dp"
    sayaki:regex="[，。？！]"
    sayaki:text="@string/text"
    sayaki:textColor="@color/colorAccent"
    sayaki:textSize="16sp"
    sayaki:textStyle="bold|italic" />
```

## License

Copyright 2016 lybeat

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.