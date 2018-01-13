import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Subjectクラス
class SubjectData implements ActionListener {
    //Integerのリスト
    private ArrayList<Integer> data;
    //【今日のPoint】Observerのリスト
    private ArrayList<Observer> obs;
    //「読み込み」ボタンのためのGUI部品
    private JFrame f1;
    private JButton b1;
    //コンストラクタ
    public SubjectData() {
        //【今日のPoint】Observerのリストを作る
        obs = new ArrayList<Observer>();
        //「読み込み」ボタンを作る
        f1 = new JFrame();
        b1 = new JButton("読み込み");
        //「読み込み」ボタンを表示する
        f1.add(b1, BorderLayout.CENTER);
        b1.addActionListener(this);
        f1.setSize(200, 100);
        f1.setVisible(true);
    }
    //dataのgetter
    public ArrayList<Integer> getData() {
        return data;
    }
    //dataのsetter
    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }
    //【今日のPoint】Observerをリストに追加する
    public void addObserver(Observer o) {
        obs.add(o);
    }
    //【今日のPoint】全てのObserverに更新を通知する
    private void notify(ArrayList<Integer> d) {
        //ArrayListの全要素のupdateメソッドを呼び出す
        for(int i=0; i<obs.size(); i++) {
            //ArrayListはgetメソッドで要素を取得できる
            Observer o = obs.get(i);
            o.update();
        }
    }
    //「読み込み」ボタンに対する処理
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        try {
            //FileChooserを開く
            int res = fc.showOpenDialog(f1);
            //「OK」ボタンが押された場合
            if(res == JFileChooser.APPROVE_OPTION) {
                //選択されたファイルを開く
                File fl = fc.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(fl));
                //dataが参照するインスタンスを新しく作り直す
                data = new ArrayList<Integer>();
                //ファイルから1行ずつ読み込んでdataのリストに追加していく
                String str;
                while((str = br.readLine()) != null) {
                    data.add(Integer.parseInt(str));
                }
                //ファイルを閉じる
                br.close();
                //【今日のPoint】Observerにdataの更新を通知する
                notify(data);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
