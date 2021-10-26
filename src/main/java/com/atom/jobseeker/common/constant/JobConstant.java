package com.atom.jobseeker.common.constant;

/**
 * @author wayan
 */
public class JobConstant {
    public enum unitEnum {
        UNIT_THOUSAND("千/月"), UNIT_TEN_THOUSAND("万/月");

        private String unit;

        unitEnum(String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return unit;
        }
    }
}
