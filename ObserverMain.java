//【今日のPoint】このアプリケーションのMainクラス
class ObserverMain {
    public static void main(String[] args) {
        //Observerを作る
        ObserverList ol = new ObserverList();
        ObserverBarGraph obg = new ObserverBarGraph();
        //Subjectを作る
        SubjectData sb = new SubjectData();

        //SubjectとObserverを相互に参照させる
        //ObserverにSubjectを追加する
        ol.setSubjectData(sb);
        obg.setSubjectData(sb);
        //SubjectにObserverを追加する
        sb.addObserver(ol);
        sb.addObserver(obg);
    }
}
