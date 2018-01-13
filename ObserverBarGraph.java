import java.util.*;
import java.awt.*;
import javax.swing.*;

//Observerクラス（棒グラフで表示するView）
class ObserverBarGraph implements Observer {
    //【今日のPoint】Subjectへの参照
    private SubjectData sb;
    //棒グラフを表示するViewのためのGUI部品
    private JFrame f1;
    private BarGraphPanel p1; //下でJPanelを継承してクラス定義している
    //コンストラクタ
    public ObserverBarGraph() {
        //リスト表示するための部品を作る
        f1 = new JFrame("ObserverBarGraph");
        p1 = new BarGraphPanel();
        //Viewを表示する
        f1.add(p1, BorderLayout.CENTER);
        f1.setSize(300, 300);
        f1.setVisible(true);                
    }
    //Subjectのgetter
    public SubjectData getSubjectData() {
        return sb;
    }
    //Subjectのsetter
    public void setSubjectData(SubjectData sb) {
        this.sb = sb;
    }
    //【今日のPoint】自身のViewを更新するためのメソッド
    public void update() {
        //repaintメソッドを呼び出すと、下で定義したpaintメソッドが呼び出される
        p1.repaint();
    }
    //棒グラフを描画するためのPanelを定義する
    public class BarGraphPanel extends JPanel {
        //paintメソッドを上書きして棒グラフの描画処理を作る
        public void paint(Graphics g) {        
            if (sb != null) {
                //SubjectDataが持つDataを取得する
                ArrayList<Integer> d = sb.getData();
                //描画する棒グラフの大きさを決める
                if(d == null || d.size() == 0) return;
                int pw = p1.getWidth();
                int ph = p1.getHeight();
                int n = d.size();
                int m = 10;
                int s = 30;
                int w = (pw-s)/n;
                int h = 3;
                //SubjectDataが持つ値から1つずつ棒グラフを描く
                for(int i=0; i<n; i++) {
                    int v = d.get(i).intValue();                    
                    //棒の中を塗りつぶす
                    g.setColor(Color.orange);
                    g.fillRect((s/2)+i*w, ph-v*h, w-m, v*h);
                    //棒の枠線を描く
                    g.setColor(Color.black);
                    g.drawRect((s/2)+i*w, ph-v*h, w-m, v*h);
                }        
            }
        }
    }
}
