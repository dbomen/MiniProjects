const uploadNewMessages = (oldMessages, newMessages) => {

    return [...oldMessages, ...newMessages];
};

// TEST
console.log(uploadNewMessages([1, 2, 3], [4, 5, 6]));

export { uploadNewMessages };
