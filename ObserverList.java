import java.util.*;
import java.awt.*;
import javax.swing.*;

//Observerクラス（リストで表示するView）
class ObserverList implements Observer {
    //【今日のPoint】Subjectへの参照
    private SubjectData sb;
    //リスト表示するViewのためのGUI部品
    private JFrame f1;
    private JList<Integer> ls;
    private JScrollPane sp;    
    //コンストラクタ
    public ObserverList() {
        //リスト表示するための部品を作る
        f1 = new JFrame("ObserverList");
        ls = new JList<Integer>();
        sp = new JScrollPane(ls);
        //Viewを表示する
        f1.add(sp, BorderLayout.CENTER);
        f1.setSize(400, 200);
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
        //リストの内容を全て消す
        ls.removeAll();
        //SubjectDataが持つDataを取得する
        ArrayList<Integer> d = sb.getData();
        //JListに追加する要素のリストmを作る
        DefaultListModel<Integer> m = new DefaultListModel<Integer>();
        //SubjectDataが持つ値をリストmに1つずつ追加する
        for(int i=0; i<d.size(); i++) {
            m.addElement(d.get(i));
        }
        //リストmをJListにセットして更新する
        ls.setModel(m);
    }
}
