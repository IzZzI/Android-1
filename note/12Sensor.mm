<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1459332396907" ID="ID_814110957" MODIFIED="1459332415406" TEXT="12Sensor">
<node CREATED="1459332533027" ID="ID_1936259679" MODIFIED="1459332548757" POSITION="right" TEXT="&#x5149;&#x7167;&#x4f20;&#x611f;&#x5668;">
<node CREATED="1459332575004" ID="ID_1275821591" MODIFIED="1459332596109" TEXT="1SensorManager"/>
<node CREATED="1459332598218" ID="ID_192343481" MODIFIED="1459332635605" TEXT="2Sensor:Sensor.TYPE_LIGHT"/>
<node CREATED="1459332636817" ID="ID_1332929291" MODIFIED="1459332655942" TEXT="3SensorEventListener">
<node CREATED="1459332666857" ID="ID_291693912" MODIFIED="1459332725687" TEXT="onSensorChanged(SensorEvent)">
<node CREATED="1459332744898" ID="ID_119299768" MODIFIED="1459332760829" TEXT="values&#x6570;&#x7ec4;"/>
</node>
<node CREATED="1459332677369" ID="ID_1742594808" MODIFIED="1459332698171" TEXT="onAccuracyChanged()"/>
</node>
<node CREATED="1459332771041" ID="ID_194743019" MODIFIED="1459332799972" TEXT="4registerListener(,,)">
<node CREATED="1459333793439" ID="ID_291974923" MODIFIED="1459333826273" TEXT="SENSOR_DELAY_UI"/>
<node CREATED="1459333826837" ID="ID_233439777" MODIFIED="1459333838411" TEXT="SENSOR_DELAY_NORMAL"/>
<node CREATED="1459333839422" ID="ID_1760551848" MODIFIED="1459333849449" TEXT="SENSOR_DELAY_GAME"/>
<node CREATED="1459333850133" ID="ID_1349726736" MODIFIED="1459333867889" TEXT="SENSOR_DELAY_FASTEST"/>
</node>
<node CREATED="1459332818296" ID="ID_1360823284" MODIFIED="1459332826661" TEXT="5unregisterListener()"/>
</node>
<node CREATED="1459332895665" ID="ID_1991660513" MODIFIED="1459332903541" POSITION="right" TEXT="&#x52a0;&#x901f;&#x5ea6;&#x4f20;&#x611f;&#x5668;">
<node CREATED="1459332909426" ID="ID_430464960" MODIFIED="1459332950996" TEXT="&#x7528;&#x6cd5;&#x540c;&#x5149;&#x7167;&#x4f20;&#x611f;&#x5668;"/>
<node CREATED="1459332952840" ID="ID_426789001" MODIFIED="1459332992173" TEXT="Sensor.TYPE_ACCELEROMETER"/>
<node CREATED="1459333003728" ID="ID_1699293570" MODIFIED="1459333020429" TEXT="values&#x6570;&#x7ec4;&#xff1a;x-y-z"/>
</node>
<node CREATED="1459333625639" ID="ID_1065926466" MODIFIED="1459333632722" POSITION="left" TEXT="&#x65b9;&#x5411;&#x4f20;&#x611f;&#x5668;">
<node CREATED="1459333665344" ID="ID_369572529" MODIFIED="1459333752018" TEXT="&#x52a0;&#x901f;&#x5ea6;+&#x5730;&#x78c1;&#x4f20;&#x611f;&#x5668;(MAGNETIC_FIELD)"/>
<node CREATED="1459333948637" ID="ID_331714309" MODIFIED="1459333991680" TEXT="SensorManager.getRotationMatrix(R,null,accValues,magValues)">
<node CREATED="1459334002596" ID="ID_1869094936" MODIFIED="1459334013305" TEXT="R:&#x957f;&#x5ea6;&#x4e3a;9"/>
</node>
<node CREATED="1459334034893" ID="ID_793185033" MODIFIED="1459334060152" TEXT="SensorManager.getOrientation(R, values)">
<node CREATED="1459334067710" ID="ID_505832227" MODIFIED="1459334086713" TEXT="values:&#x957f;&#x5ea6;&#x4e3a;3&#xff0c;z-x-y"/>
</node>
<node CREATED="1459334139676" ID="ID_315800179" MODIFIED="1459334171096" TEXT="Math.toDegrees(values[])"/>
</node>
</node>
</map>