package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Controller
{
    @FXML
    private TextField txtEnterTime;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;

    public static int sec,min,hour;
    public String ddSec,ddMin,ddHour;
    public DecimalFormat dFormat = new DecimalFormat("00");
    public Timer t;
    @FXML
    void pressStart(ActionEvent e)
    {
            hour = Integer.parseInt(txtEnterTime.getText())/60;
            min = Integer.parseInt(txtEnterTime.getText())%60;
            sec=0;
            t = new Timer();
            t.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            sec--;
                            ddHour = dFormat.format(hour);
                            ddMin = dFormat.format(min);
                            ddSec = dFormat.format(sec);
                            lblTime.setText(ddHour+":"+ddMin+":"+ddSec);
                            if(sec==-1)
                            {
                                sec=59;
                                min--;
                                ddSec = dFormat.format(sec);
                                ddMin = dFormat.format(min);
                                lblTime.setText(ddHour+":"+ddMin+":"+ddSec);
                            }
                            if(min==-1)
                            {
                                hour--;
                                sec=59;
                                min=59;
                                ddSec = dFormat.format(sec);
                                ddMin = dFormat.format(min);
                                ddHour = dFormat.format(hour);
                                lblTime.setText(ddHour+":"+ddMin+":"+ddSec);
                            }
                            if(sec==0&&min==0&hour==0)
                            {
                                min=2;
                                hour=0;
                                sec=0;
                                t.cancel();
                                cancel();
                                if(true)
                                {
                                    t = new Timer();
                                    t.schedule(new TimerTask()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            Platform.runLater(new Runnable()
                                            {
                                                @Override
                                                public void run()
                                                {
                                                    sec--;
                                                    ddHour = dFormat.format(hour);
                                                    ddMin = dFormat.format(min);
                                                    ddSec = dFormat.format(sec);
                                                    lblTime.setText(ddHour+":"+ddMin+":"+ddSec);
                                                    if(sec==-1)
                                                    {
                                                        sec=59;
                                                        min--;
                                                        ddSec = dFormat.format(sec);
                                                        ddMin = dFormat.format(min);
                                                        lblTime.setText(ddHour+":"+ddMin+":"+ddSec);
                                                    }
                                                    if(min==-1)
                                                    {
                                                        hour--;
                                                        sec=59;
                                                        min=59;
                                                        ddSec = dFormat.format(sec);
                                                        ddMin = dFormat.format(min);
                                                        ddHour = dFormat.format(hour);
                                                        lblTime.setText(ddHour+":"+ddMin+":"+ddSec);
                                                    }
                                                    if(sec==0&&min==0&hour==0)
                                                    {
                                                        lblTime.setText("Time Is Finished");
                                                        t.cancel();
                                                        cancel();
                                                    }
                                                }
                                            });

                                        }
                                    },0,100);
                                }
                                lblTime.setText("Time Is Finished");
                            }
                        }
                    });

                }
            },0,100);
    }
    @FXML
    void pressStop(ActionEvent e)
    {
        //timerTask.cancel();
        t.cancel();
    }
}
