package jp.co.eintecs.beans;

import java.io.Serializable;

/**
 * 税率情報を入れるコンテナ
 * @author sugie
 * @author tak
 * @author sasaki
 */

public class TaxBean implements Serializable {
	//フィールド
	private static final long serialVersionUID = 1L;//バージョン番号
	private double tax;


	//コンストラクタ
	public TaxBean() {
	}


	//メソッド
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
}
