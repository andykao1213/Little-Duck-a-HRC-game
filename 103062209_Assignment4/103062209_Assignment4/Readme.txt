第一部份:
	1.MyWindow：建立視窗，包含txt(JTextField)，GameStage(JPanel)，Typing(JPanel)。
	2.GameStage: 實作右邊小鴨的動畫，在使用者答對時，利用布景移動製造出小鴨向前的效果。並利用小鴨位置的上下製造漂移的效果
	3.Typing:實作打字動畫以及處理textfile的值
	4.Words:處理圖檔資料庫，新增使用者辨識出來的單字並新增至"output.txt"
	GUI的不分較不熟悉，一開始因為把textfield畫在panel上而無法自行設定位置，後來才改直接加在jframe上，
	自己GUI的觀念需多加強；file I/O也不大熟悉，查了很多字料才知道如何解讀。
第二部分：
	處理文字內容比較麻煩，我是利用String的spilt()一一拆解，把每一行拆解為row, col, 文字意義。存取就簡單多了，我開一個15*15的array，每次就按照array[row][col] = 文字
	的方式存取，而沒有文字或根本不存在的則以"_"輸出