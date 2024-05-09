package db_lab.data;

public final class Queries {

    public static final String TAGS_FOR_PRODUCT =
        """
        select t.tag_name
        from TAGGED t
        where t.product_code = ?
        """;

    public static final String LIST_PRODUCTS =
        """
        select p.code,p.name
        from PRODUCT p
        """;

    public static final String PRODUCT_COMPOSITION =
        """
        select m.code,c.percent,m.description
        from   COMPOSITION c, MATERIAL m
        where  c.material_code = m.code
        and    c.product_code = ?
        """;

    public static final String FIND_PRODUCT =
        """
        select p.code,p.name,p.description
        from PRODUCT p
        where p.code = ?
        """;
}
