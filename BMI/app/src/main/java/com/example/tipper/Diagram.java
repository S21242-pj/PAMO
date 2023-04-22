package com.example.tipper;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Diagram extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagram);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 22),
                new DataPoint(1, 23),
                new DataPoint(2, 24),
                new DataPoint(3, 25),

        });
        graph.addSeries(series);

        staticLabelsFormatter.setHorizontalLabels(new String[]{"01-01-2023", "01-02-2023", "01-03-2023"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        series.setColor(Color.rgb(0, 80, 100));
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(5);
        series.setThickness(5);
        graph.addSeries(series);
        graph.setTitle("BMI progress");
        graph.setTitleTextSize(50);
        graph.setTitleColor(Color.RED);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("X time");
        gridLabel.setVerticalAxisTitle("Y BMI");

    }

}

