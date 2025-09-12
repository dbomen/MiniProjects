function addID(campaignRecord) {

    campaignRecord.id = `${campaignRecord.campaignName}-${campaignRecord.senderName}`
}

// don't touch below this line

export { addID };
