package com.example.administrator.linechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart =this.findViewById(R.id.linechart);
        showLineChart(getData(),lineChart);
    }

    private HashMap<String, List<Entry>> getData(){
        //造数据
        List<Entry> point =new ArrayList<>();
        for (int i=1;i<10;i++){
            point.add(new Entry(i, (float) ((Math.random())*20)));
        }
        HashMap<String, List<Entry>> map =new HashMap<>();
        map.put("one",point);

        List<Entry> point1 =new ArrayList<>();
        for (int i=1;i<10;i++){
            point1.add(new Entry(i, (float) ((Math.random())*20)));
        }
        map.put("two",point1);
        return map;
    }
    private void showLineChart(HashMap<String, List<Entry>> map,LineChart lineChart){




        //载入map中point的数据，并设置颜色
        LineDataSet lineDataSet=new LineDataSet(map.get("one"),"这是线段一");
        lineDataSet.setColor(Color.rgb(199,255,140));

        //载入map中point1的数据，并设置颜色
        LineDataSet lineDataSet1=new LineDataSet(map.get("two"),"这是线段二");
        lineDataSet1.setColor(Color.rgb(255,240,157));

        LineData lineData=new LineData();
        lineData.addDataSet(lineDataSet);
        lineData.addDataSet(lineDataSet1);
        lineChart.setData(lineData);

        //设置下方图标
        Description description=new Description();
        description.setText("折线图表");
        lineChart.setDescription(description);

        //这里是设置折线图的摆放位置，可以用它来图的大小
        lineChart.setExtraOffsets(25,80,28,80);

        //设置x轴
        XAxis xAxis=lineChart.getXAxis();
        //设置x轴摆放位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置x轴的最小最大位置的值
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(map.get("one").size()+1);
        //设置列数
        xAxis.setLabelCount(map.get("one").size());

        //设置行数和最小显示值
        lineChart.getAxisLeft().setLabelCount(map.get("one").size());
        lineChart.getAxisLeft().setAxisMinimum(0);

        //关闭y轴右坐标，以及横线的显示
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);

      //设置图表不能缩放，刷新数据
        lineChart.setScaleEnabled(false);
        lineChart.invalidate();

    }

}
