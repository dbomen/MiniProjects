try {
    const leaderboard = await fetchLeaderBoard();
    console.log(leaderboard);
} catch (error) {
    console.log("Our servers are down, but we will be up and running soon");
}

// don't touch below this line

async function fetchLeaderBoard(): Promise<any> {
    const response = await fetch("https://jello.servers");
    return response.json();
}

export { };
