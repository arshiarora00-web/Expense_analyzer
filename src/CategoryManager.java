public class CategoryManager {

    public static String getCategory(String description) {

        String text = description.toLowerCase();

        // Food Category
        if (text.contains("pizza") || text.contains("burger") ||
                text.contains("food") || text.contains("grocery") ||
                text.contains("restaurant") || text.contains("lunch") ||
                text.contains("dinner") || text.contains("coffee")) {

            return "Food";
        }

        // Transportation Category
        else if (text.contains("bus") || text.contains("taxi") ||
                text.contains("petrol") || text.contains("fuel") ||
                text.contains("train") || text.contains("metro") ||
                text.contains("auto")) {

            return "Transportation";
        }

        // Shopping Category
        else if (text.contains("amazon") || text.contains("clothes") ||
                text.contains("shoes") || text.contains("shopping") ||
                text.contains("flipkart")) {

            return "Shopping";
        }

        // Education Category
        else if (text.contains("books") || text.contains("college") ||
                text.contains("fees") || text.contains("stationery") ||
                text.contains("course")) {

            return "Education";
        }

        // Entertainment Category
        else if (text.contains("movie") || text.contains("cinema") ||
                text.contains("games") || text.contains("subscription")) {

            return "Entertainment";
        }

        // Healthcare Category
        else if (text.contains("medicine") || text.contains("doctor") ||
                text.contains("hospital") || text.contains("pharmacy")) {

            return "Healthcare";
        }

        // Other Category
        else {

            return "Other";
        }
    }
}