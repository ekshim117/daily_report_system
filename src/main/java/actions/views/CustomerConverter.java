package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Customer;

//顧客データのDTOモデルとViewモデルの変換を行うクラス

public class CustomerConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンス生成
     * @param cv CustomerViewのインスタンス
     * @return Customerのインスタンス
     */

    public static Customer toModel(CustomerView cv) {

        return new Customer(
                cv.getId(),
                cv.getCode(),
                cv.getName(),
                cv.getSort(),
                cv.getCreatedAt(),
                cv.getUpdatedAt()
                );
    }
    /**
     * DTOモデルのインスタンスからViewモデルのインスタンス生成
     * @param c Customerのインスタンス
     * @return CustomerViewのインスタンス
     */
    public static CustomerView toView(Customer c) {
        if(c == null) {
            return null;
        }
        return new CustomerView(
                c.getId(),
                c.getCode(),
                c.getName(),
                c.getSort(),
                c.getCreatedAt(),
                c.getUpdatedAt()
                );
    }
    /**
     * DTOモデルのリストからViewモデルのリスト生成
     * @param c Customerのリスト
     * @return CustomerViewのリスト
     */
    public static List<CustomerView> toVIewList(List<Customer> list){
        List<CustomerView> cvs = new ArrayList<>();
        for (Customer c : list) {
            cvs.add(toView(c));
        }

        return cvs;
    }
    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param c DTOモデル(コピー先)
     * @param cv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Customer c, CustomerView cv) {
        c.setId(cv.getId());
        c.setCode(cv.getCode());
        c.setName(cv.getName());
        c.setSort(cv.getSort());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
    }

}
