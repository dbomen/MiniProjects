function addToPhonebook(phoneNumber, name, phoneBook) {

    const newPhoneBook = new Map(phoneBook);
    newPhoneBook.set(phoneNumber, name);
    return newPhoneBook;
}

// TEST
console.log(addToPhonebook("555-1111", "Geralt",
    new Map([["555-2222", "Ciri"]])));

// don't touch below this line

export { addToPhonebook };
