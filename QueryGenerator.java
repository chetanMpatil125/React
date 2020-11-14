import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class QueryGenerator {

	public static void main(String[] args) {
		BufferedReader inputFile;
		PrintWriter queryFile;
		try {
			String query1 = "INSERT INTO `tomtomtbsubscription` (`ORDER_REFERENCE`, `DURATION_VALUE`, "
					+ "`ARTICLE_SKU`, `ORDER_STATUS`, `CHANNEL_ID`, `CURRENCY`, `GROSS_AMOUNT`, "
					+ "`TAX_AMOUNT`, `DISCOUNT_AMOUNT`, `CREATION_TIME`, `MODIFICATION_TIME`, "
					+ "`CREATED_BY`, `START_DATE`, `END_DATE`, `SERVICE_CATEGORY`, "
					+ "`PAYMENT_FREQUENCY_IN_MONTHS`, `DURATION_UOM`, `CANCELLATION_DATE`, "
					+ "`MODIFIED_BY`, `TAX_PERCENTAGE`, `ARTICLE_NAME`, `VOUCHER_CODE`, "
					+ "`REDOWNLOAD_DURATION_UOM`, `REDOWNLOAD_DURATION_VALUE`, `ARTICLE_QUANTITY`, "
					+ "`MINIMUM_DURATION_UOM`, `MINIMUM_DURATION_VALUE`, `NOTICE_PERIOD_UOM`, "
					+ "`NOTICE_PERIOD_VALUE`, `SUBS_STARTDATE_INDICATION`, `GRACE_PERIOD_IN_DAYS`, "
					+ "`ORDER_DATE`, `ARTICLE_GROUP`, `MAP_PRODUCT_CODE`, `ACCOUNT_FK`, "
					+ "`SERVICE_TYPE_FK`, `SUBSCRIPTION_FK`, `STATUS_REASON_CODE`, "
					+ "`CANCELLATION_BATCH_STATUS`, `CANCELLATION_MODIFIED_BY`, "
					+ "`CANCELLATION_MODIFICATION_TIME`, `CANCELLATION_RIGHT_NOW_CASE_NU`, "
					+ "`CANCELLATION_CREATION_TIME`, `CANCELLATION_AGENT_EMAIL`, "
					+ "`COMPENSATION_RN_CASE_NUM` ) Values( NULL, 15, \"1SW0.002.40\", 141, "
					+ "\"1N.sE2RwqKEAAAFODS9hAEkL\", \"USD\", 0, 0, 0, NOW(), NOW(), "
					+ "\"subs_administration\", NOW(), DATE_ADD(NOW(),INTERVAL 15 YEAR), 3, 0, \"Y\","
					+ " \"1970-01-02 01:00:00\", 5, 0, \"EV_POI\", NULL, NULL, 0, 1, NULL, 0, NULL, 0,"
					+ " 0, 0, NOW(), 5, NULL, <<Account ID>>, 1, NULL, 0, NULL, 0,"
					+ " \"1970-01-02 01:00:00\", NULL, \"1970-01-02 01:00:00\", NULL, 123456);";
			String query2 = "INSERT INTO `tomtomtbrightinterval` ( `ARTICLE_SKU`, `STATUS`, "
					+ "`MODIFICATION_TIME`, `START_DATE`, `END_DATE`, `CREATION_TIME`, `CREATED_BY`, "
					+ "`MODIFIED_BY`, `SERVICE_CATEGORY`, `MAP_PRODUCT_CODE`, `ACCOUNT_FK`, "
					+ "`SERVICE_TYPE_FK`, `PROMOTION_FK`, `SUBSCRIPTION_FK`, `RIGHT_INTERVAL_FK` ) "
					+ "SELECT \\\"1SW0.002.40\\\", 139, tb1.MODIFICATION_TIME, tb1.START_DATE, "
					+ "tb1.END_DATE, tb1.CREATION_TIME, tb1.CREATED_BY, tb1.MODIFIED_BY, "
					+ "tb1.SERVICE_CATEGORY, NULL, tb1.ACCOUNT_FK, tb1.SERVICE_TYPE_FK, NULL, "
					+ "tb1.SUBSCRIPTION_ID, NULL FROM tomtomtbsubscription tb1 WHERE  "
					+ "tb1.ARTICLE_SKU = '1SW0.002.40' AND FLOOR(DATEDIFF(tb1.END_DATE, "
					+ "tb1.START_DATE)/365) = 15 AND tb1.ACCOUNT_FK = <<Account ID>>;";
			inputFile = new BufferedReader(new FileReader("C:/Users/mutalikr/Desktop/5362/Prod-AccountId.txt"));

			int i = 0, j = 0;
			String tmp = null;

			queryFile = new PrintWriter("C:/Users/mutalikr/Desktop/5362/query_" + j + ".txt");
			while ((tmp = inputFile.readLine()) != null) {
				query1 = query1.replace("<<Account ID>>", "tmp");
				query2 = query2.replace("<<Account ID>>", "tmp");
				System.out.println("Read == " + tmp + ":" + j);
				i++;
				if ((i % 20) == 0) {
					queryFile.close();
					j++;
					queryFile = new PrintWriter("C:/Users/mutalikr/Desktop/5362/query_" + j + ".txt");
				}
				queryFile.println(query1 + "\n" + query2);
			}
			inputFile.close();
			queryFile.close();
			System.out.println("Total lines == " + i);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
