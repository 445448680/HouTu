package me.zhyd.houtu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GHS
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Template implements Cloneable {

    private String filePath;
    private String fileContent;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
