public class CurrentUser {
    private static User currentUser = null;

    /**
     * Sets the current user of the system.
     * @param user The user who has logged in.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Returns the currently logged-in user.
     * @return The current user.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Logs out the current user by setting the currentUser to null.
     */
    public static void logout() {
        currentUser = null;
    }
}
