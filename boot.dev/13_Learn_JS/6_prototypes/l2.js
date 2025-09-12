const user = {
    name: "Default User",
    type: "user",
};

const adminUser = Object.create(user);
adminUser.type = "admin";

function isAdmin(object) {

    if (Object.getPrototypeOf(object).type == "admin") return true;
    return false;
}

// TEST
const adminInstance = Object.create(adminUser);
console.log(isAdmin(adminInstance) + " == true");

const userInstance = Object.create(user);
console.log(isAdmin(userInstance) + " == false");

// don't touch below this line

export { user, adminUser, isAdmin };
