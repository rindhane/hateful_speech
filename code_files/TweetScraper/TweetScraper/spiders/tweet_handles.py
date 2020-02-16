def queries():
    #add desired twitter_handles to be added for the scraping .
    old = [
        "nytimes",
        "bbc",
        "cnn",
        "Literature",
        "EconomicTimes",
        "Harvard",
        "NatGeoChannel",
        "businessinsider",
        "TheEconomist"
    ]
    
    new = [
        "NobelPrize",
        "FinancialTimes",
        "WSJ",
        "Reuters",
        "the_hindu",
        "IAF_MCC",
        "narendramodi",
        "Discovery",
        "NASA",
        "ShashiTharoor",
        "ZubaanBooks",
        "HarvardBooks",
        "PenguinIndia",
        "incredibleindia",
        "lonelyplanet_in",
        "NGTIndia",
        "PopSci",
        "BloombergQuint"
        "Disney",
        "juggernautbooks",
        "ParamountPics",
        "airnewsalerts",
        "AJENews",
        "BBCWorld",
        "NatGeo",
        "nature",
        "NatureNV",
        "DiscussingFilm",
        "DarkReading",
        "SHOUTmyBook",
        "dialoguebooks",
        "HutchinsonBooks",
        "washingtonpost",
        "guardian",
        "TheQuint",
        "livemint",
        "ITGDsports",
        "bsindia",
        "toisports",
        "TheQuint",
        "scroll_in",
        "thewire_in",
        "ThePrintIndia",
    ]
    new.extend(old)

    return new