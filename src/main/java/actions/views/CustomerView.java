package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 顧客情報について画面の入出力値を扱うViewモデル
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerView {

    /**
     * id
     */
    private Integer id;

    /**
     * 顧客番号
     */
    private String code;

    /**
     * 氏名
     */
    private String name;

    /**
     * 表示順
     */
    private String sort;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;





}
