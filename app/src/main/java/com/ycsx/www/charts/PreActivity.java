package com.ycsx.www.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PreActivity extends AppCompatActivity {
    private PieChartView pieChart;
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<SliceValue>();
    private int[] data = {21, 33, 16, 6, 24};
    private int[] color = {Color.parseColor("#33B5E5"), Color.parseColor("#AA66CC"), Color.parseColor("#99CC00"),
            Color.parseColor("#FFBB33"), Color.parseColor("#FF4444")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        pieChart = (PieChartView) findViewById(R.id.pie_chart);
        pieChart.setOnValueTouchListener(selectListener);//设置点击事件监听

        setPieChartData();
        initPieChart();

    }


    /**
     * 获取数据
     */
    private void setPieChartData() {
        for (int i = 0; i < data.length; ++i) {
            //SliceValue sliceValue = new SliceValue((float) data[i], ChartUtils.pickColor());//随机选择颜色
            SliceValue sliceValue = new SliceValue((float) data[i], color[i]);
            values.add(sliceValue);
        }
    }

    /**
     * 初始化
     */
    private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pieChardata.setCenterText1("饼图");//环形中间的文字1
        pieChardata.setCenterText1Color(Color.BLACK);//文字颜色
        pieChardata.setCenterText1FontSize(14);//文字大小

//        pieChardata.setCenterText2("饼图测试");
//        pieChardata.setCenterText2Color(Color.BLACK);
//        pieChardata.setCenterText2FontSize(18);
        /**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//      Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");
//      data.setCenterText1Typeface(tf);

        pieChart.setPieChartData(pieChardata);
        pieChart.setValueSelectionEnabled(true);//选择饼图某一块变大
        pieChart.setAlpha(0.9f);//设置透明度
        pieChart.setCircleFillRatio(1f);//设置饼图大小

    }


    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            Toast.makeText(PreActivity.this, "Selected: " + value.getValue(), Toast.LENGTH_SHORT).show();
        }
    };


}
