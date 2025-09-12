const getMostRecentUser = (usernames) => {

    if (usernames.length == 0) return null;
    else return usernames[usernames.length - 1];
};

// TEST
console.log("null == " + getMostRecentUser([]));
console.log("Legolas == " + getMostRecentUser(["Frodo", "Gandalf", "Legolas"]));
console.log("Geralt == " + getMostRecentUser(["Geralt"]));

export { getMostRecentUser };
