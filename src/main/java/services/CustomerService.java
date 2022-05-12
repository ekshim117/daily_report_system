package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import constants.JpaConst;
import models.Customer;
import models.validators.CustomerValidator;

public class CustomerService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、CustomerViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<CustomerView> getPerPage(int page) {
        List<Customer> customers = em.createNamedQuery(JpaConst.Q_CUS_GET_ALL, Customer.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return CustomerConverter.toViewList(customers);
    }
    /**
     * 顧客テーブルのデータの件数を取得し、返却する
     * @return 顧客テーブルのデータの件数
     */
    public long countAll() {
        long cusCount = (long) em.createNamedQuery(JpaConst.Q_CUS_COUNT, Long.class)
                .getSingleResult();

        return cusCount;
    }

    /**
     * idを条件に取得したデータをCustomerViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public CustomerView findOne(int id) {
        Customer c = findOneInternal(id);
        return CustomerConverter.toView(c);
    }

    /**
     * 顧客番号を条件に該当するデータの件数を取得し、返却する
     * @param code 顧客番号
     * @return 該当するデータの件数
     */
    public long countByCode(String code) {

        //指定した顧客番号を保持する顧客の件数を取得する
        long customers_count = (long) em.createNamedQuery(JpaConst.Q_CUS_COUNT_RESISTERED_BY_CODE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, code)
                .getSingleResult();
        return customers_count;
    }

    /**
     * 画面から入力された顧客の登録内容を元にデータを1件作成し、顧客テーブルに登録する
     * @param cv 画面から入力された顧客の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(CustomerView cv, String pepper) {




        //登録日時、更新日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        cv.setCreatedAt(now);
        cv.setUpdatedAt(now);

      //登録内容のバリデーションを行う
        List<String> errors = CustomerValidator.validate(this, cv, true, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(cv);
        }

        //エラーを返却（エラーがなければ0件の空リスト）


        return errors;


    }

    /**
     * 画面から入力された顧客の登録内容を元にデータを1件作成し、顧客テーブルに更新する
     * @param cv 画面から入力された顧客の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */

   public List<String> update(CustomerView cv, String pepper){
       //idを条件に登録済みの顧客情報を取得する
       CustomerView savedCus = findOne(cv.getId());

       boolean validateCode = false;
       if (!savedCus.getCode().equals(cv.getCode())) {
           //顧客番号を更新する場合

           //顧客番号についてのバリデーションを行う
           validateCode = true;
           //変更後の顧客番号を設定する
           savedCus.setCode(cv.getCode());
       }

       boolean validatePass = false;


       savedCus.setName(cv.getName()); //変更後の氏名を設定する


       //更新日時に現在時刻を設定する
       LocalDateTime today = LocalDateTime.now();
       savedCus.setUpdatedAt(today);

       //更新内容についてバリデーションを行う
       List<String> errors = CustomerValidator.validate(this, savedCus, validateCode, validatePass);

       //バリデーションエラーがなければデータを更新する
       if (errors.size() == 0) {
           update(savedCus);
       }

       //エラーを返却（エラーがなければ0件の空リスト）
       return errors;
   }

   /**
    * idを条件に顧客データを論理削除する
    * @param id
    */
   public void destroy(Integer id) {

       //idを条件に登録済みの従業員情報を取得する
       CustomerView savedCus = findOne(id);

       //更新日時に現在時刻を設定する
       LocalDateTime today = LocalDateTime.now();
       savedCus.setUpdatedAt(today);

       //論理削除フラグをたてる
       //savedCus.setDeleteFlag(JpaConst.CUS_DEL_TRUE);

       //更新処理を行う
       update(savedCus, null);

   }
   /**
    * 顧客番号とパスワードを条件に検索し、データが取得できるかどうかで認証結果を返却する
    * @param code 顧客番号
    * @param plainPass パスワード
    * @param pepper pepper文字列
    * @return 認証結果を返却す(成功:true 失敗:false)
    */
   public Boolean validateLogin(String code, String plainPass, String pepper) {

       boolean isValidCustomer = false;
       /*if (code != null && !code.equals("") && plainPass != null && !plainPass.equals("")) {
           CustomerView cv = findOne(code, plainPass, pepper);

           if (cv != null && cv.getId() != null) {

               //データが取得できた場合、認証成功
               isValidCustomer = true;
           }
       }*/

       //認証結果を返却する
       return isValidCustomer;
   }
   /**
    * idを条件にデータを1件取得し、Customerのインスタンスで返却する
    * @param id
    * @return 取得データのインスタンス
    */
   private Customer findOneInternal(int id) {
       Customer c = em.find(Customer.class, id);

       return c;
   }

   /**
    * 顧客データを1件登録する
    * @param cv 顧客データ
    * @return 登録結果(成功:true 失敗:false)
    */
   private void create(CustomerView cv) {

       em.getTransaction().begin();
       em.persist(CustomerConverter.toModel(cv));
       em.getTransaction().commit();

   }

   /**
    * 顧客データを更新する
    * @param cv 画面から入力された顧客の登録内容
    */
   private void update(CustomerView cv) {

       em.getTransaction().begin();
       Customer c = findOneInternal(cv.getId());
       CustomerConverter.copyViewToModel(c, cv);
       em.getTransaction().commit();

   }

}
