package br.com.posts.endpoint.enums;

public enum TypePostEnum {

    FINANCEIRO(1),
    MAO_DE_OBRA(2);

    public final Integer id;

    private TypePostEnum(Integer id) {
        this.id = id;
    }

    public static TypePostEnum valueOfId(Integer id) {
        for (TypePostEnum e : values()) {
            if (e.id.compareTo(id) == 0) {
                return e;
            }
        }
        return null;
    }

}
