package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 従業員データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CUS)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CUS_GET_ALL,
            query = JpaConst.Q_CUS_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_CUS_COUNT,
            query = JpaConst.Q_CUS_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_CUS_COUNT_RESISTERED_BY_CODE,
            query = JpaConst.Q_CUS_COUNT_RESISTERED_BY_CODE_DEF),

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Customer {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CUS_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 顧客番号
     */
    @Column(name = JpaConst.CUS_COL_CODE, nullable = false, unique = true)
    private String code;

    /**
     * 氏名
     */
    @Column(name = JpaConst.CUS_COL_NAME, nullable = false)
    private String name;

    /**
     * 表示順  全ての顧客をidの降順に取得する
     */
    @Column(name = JpaConst.CUS_COL_SORT, nullable = false)
    private String sort;

    /**
     *登録日時
     */
    @Column(name = JpaConst.CUS_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.CUS_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;




}
