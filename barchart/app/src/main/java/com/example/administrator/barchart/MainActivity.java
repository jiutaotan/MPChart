package com.example.administrator.barchart;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barChart=this.findViewById(R.id.barchart);
        showBarChart(getData(),barChart);
    }
    private Map<String, List<BarEntry>> getData(){
        //造了两条数据
        List<BarEntry> list=new ArrayList<>();
        for (int i=1;i<=9;i=i+2){
            list.add(new BarEntry(i, (float) Math.round((Math.random())*10)+1));
        }
        List<BarEntry> list1=new ArrayList<>();
        for (int i=2;i<=10;i=i+2){
            list1.add(new BarEntry(i, (float) Math.round((Math.random())*10)+1));
        }
        HashMap<String, List<BarEntry>> map=new HashMap<>();
        map.put("man",list);
        map.put("girl",list1);
        return map;
    }
    private void showBarChart(Map<String,List<BarEntry>> map,BarChart barChart){
        //装载男女生数据
        BarDataSet barDataSet=new BarDataSet(map.get("man"),"男生");
        barDataSet.setColor(Color.rgb(199,255,140));

        BarDataSet barDataSet1=new BarDataSet(map.get("girl"),"女生");
        barDataSet1.setColor(Color.rgb(255,240,157));

        //载入数据集
        BarData barData=new BarData();
        barData.addDataSet(barDataSet);
        barData.addDataSet(barDataSet1);
        barChart.setData(barData);

        //可以用它来设置图的大小显示
        barChart.setExtraOffsets(15,80,20,45);

        //右下角标识描述,需要可以打开
        Description description=new Description();
        description.setText("柱状图");
        description.setEnabled(false);
        barChart.setDescription(description);

        //将x轴设置在下方
        XAxis xAxis=barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        xAxis.setLabelCount(10);

        //设置y轴右边不显示
        YAxis yAxis=barChart.getAxisRight();
        yAxis.setEnabled(false);

        YAxis yy=barChart.getAxisLeft();
        yy.setAxisMinimum(0);
        yy.setAxisMaximum(10);
        yy.setLabelCount(100);
    }

}
