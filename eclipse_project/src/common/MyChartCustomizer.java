package common;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

public class MyChartCustomizer implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart jfChart, JRChart jrChart) {
        final CategoryPlot plot = jfChart.getCategoryPlot();
        
        // x axis in chart have another name is domain axis
        final CategoryAxis categoryAxis = plot.getDomainAxis();
        
        // get number of labels will appearance in report
        final int labels = plot.getCategories().size();
        
        // create transparent color -> make label look like disappearance
        final Color noColor = new Color(1f, 1f, 1f, 0f);
        
        final int maximumLabels = 6;
        final int ticketDisplayRatio 
            = labels <= maximumLabels
                ? 1 
                : labels <= 2 * maximumLabels 
                    ? 2 
                    : labels / maximumLabels
        ;
        for (int i = labels - 1; i > -1; i--) {
            // make unexpected label transparent
            if ((i % ticketDisplayRatio) != 0) {
                String categoryLabel = (String) plot.getCategories().get(i);
                categoryAxis.setTickLabelPaint(categoryLabel, noColor);
            }
        }
    }

}
