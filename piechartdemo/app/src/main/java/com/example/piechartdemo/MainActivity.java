package com.example.piechartdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart=this.findViewById(R.id.pie_chart);
        showPieChart(pieChart,getPieChartData());
    }
    private List<PieEntry> getPieChartData() {
//       用于存放pieEntry
        List<PieEntry> mPie = new ArrayList<>();
        //下面这是我自己造的假数据
        HashMap<String,Float> map=new HashMap<>();
        map.put("A",10f);
        map.put("B",20f);
        map.put("C",40f);
        map.put("D",30f);
        for (Map.Entry<String, Float> entry: map.entrySet()){
            //第一个参数是图表块的值，第二个参数是图标块的名称
            PieEntry pieEntry = new PieEntry( entry.getValue(),entry.getKey());
            mPie.add(pieEntry);
        }
        return mPie;
    }
    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {

        PieDataSet dataSet = new PieDataSet(pieList,"Label");
        // 用来存储颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
       //遍历颜色
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述
        Description description = new Description();
        description.setText("这是你设置的标识");
        description.setEnabled(true);
        pieChart.setDescription(description);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        //数据连接线距图形片内部边界的距离，为百分数
//        dataSet.setValueLinePart1OffsetPercentage(80f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.LTGRAY);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        //高光设置
        dataSet.setHighlightEnabled(true);

        // 不显示图例,图例即图下方标记
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据，可以靠他来调整位置
        pieChart.setExtraOffsets(28, 5, 35, 5);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束,可以在EasingOption中选着加载的样式
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        pieChart.setDrawCenterText(true);


        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());//设置格式为百分百格式
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }
}
