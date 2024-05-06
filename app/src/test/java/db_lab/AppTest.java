/*
 * This source file was generated by the Gradle 'init' task
 */
package db_lab;

import static org.assertj.core.api.Assertions.*;

import db_lab.data.DAOUtils;
import db_lab.data.Material;
import db_lab.data.Product;
import db_lab.data.ProductPreview;
import db_lab.data.Tag;
import db_lab.model.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public final class AppTest {

    private static Connection connection;

    @BeforeClass
    public static void setup() {
        connection = DAOUtils.localMySQLConnection("tessiland", "root", "");
    }

    @AfterClass
    public static void cleanup() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void productTags() {
        var actual = Tag.DAO.listForProduct(connection, 1);
        var expected = List.of(new Tag("tag1"), new Tag("tag2"));
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void productComposition() {
        var actual = Material.DAO.forProduct(connection, 1);
        var expected = Map.ofEntries(
            Map.entry(new Material(1, "linen"), 0.6f),
            Map.entry(new Material(2, "cotton"), 0.4f)
        );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void productPreviews() {
        var actual = ProductPreview.DAO.list(connection);
        var expected = List.of(
            new ProductPreview(1, "a", List.of(new Tag("tag1"), new Tag("tag2"))),
            new ProductPreview(2, "b", List.of(new Tag("tag3"), new Tag("tag4")))
        );
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void product() {
        var actual = Product.DAO.find(connection, 1);
        var expectedComposition = Map.ofEntries(
            Map.entry(new Material(1, "linen"), 0.6f),
            Map.entry(new Material(2, "cotton"), 0.4f)
        );
        var expected = new Product(1, "a", "description a", expectedComposition);
        assertThat(actual).isPresent().hasValue(expected);
    }
}
