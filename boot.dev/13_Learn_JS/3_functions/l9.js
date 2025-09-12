function createContact(phoneNumber, name = "Anonymous", avatar = "default.jpg") {

    if (phoneNumber == "") return "Invalid phone number";
    else {

        avatar = "/public/pictures/" + avatar;
        return `Contact saved! Name: ${name}, Phone number: ${phoneNumber}, Avatar: ${avatar}`;
    }
}

// don't touch below this line

export { createContact };
