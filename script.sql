CREATE DATABASE [J3LP0001]
GO
 USE [J3LP0001]
GO
/****** Object:  Table [dbo].[Answer]    Script Date: 2/28/2021 12:25:37 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer](
	[aid] [varchar](50) NOT NULL,
	[qid] [varchar](50) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[isTrue] [bit] NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 2/28/2021 12:25:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[qid] [varchar](50) NOT NULL,
	[userName] [varchar](50) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[created] [date] NOT NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[qid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 2/28/2021 12:25:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[userName] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[email] [varchar](max) NOT NULL,
	[isTeacher] [bit] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'odsaiwccmxvcadc', N'xfzmhumnzozlwec', N'Hanukkah', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'wwyjtkfgftpmaus', N'xfzmhumnzozlwec', N'Yom Kippur', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'vygvihbqjskhraq', N'xfzmhumnzozlwec', N'Kwanza', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'eolgvehcsupkcpl', N'xfzmhumnzozlwec', N'Rosh Hashanah', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'pcualcvhdcynaue', N'thsohqeqfxycere', N'6', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'ouxymzaatvjjgth', N'thsohqeqfxycere', N'7', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'zdgnoolvvcnhuyk', N'ipvykkpeexxhrjr', N'Ron Weasley', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'vfhryzdyrxbntgw', N'ipvykkpeexxhrjr', N'Neville Longbottom', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'embzqqjjqxujnqx', N'ipvykkpeexxhrjr', N'Draco Malfoy', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'rzmdyepugkgpson', N'ipvykkpeexxhrjr', N'Hermione Granger', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'vuouunfunhqnets', N'wnemuwobgxbjnlb', N'King Harvest', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'vxzlbdzfgkcohss', N'wnemuwobgxbjnlb', N'Spectrums', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'nrolrmxtiwaahbj', N'wnemuwobgxbjnlb', N'Commodores', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'xjhnxnmootqcbua', N'wnemuwobgxbjnlb', N'The Marshall Tucker Band', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'lrjrfuthjwtlaoa', N'thsohqeqfxycere', N'13', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'ngawkstsotsekqu', N'thsohqeqfxycere', N'0', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'uwycbwgautomvvk', N'zgxyaeoyxvgzxpn', N'Dragon', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'lqdgkgceiepplui', N'zgxyaeoyxvgzxpn', N'Rabbit', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'ahvzmdwlbsvcieh', N'zgxyaeoyxvgzxpn', N'Dog', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'unxkjhslhbithdm', N'zgxyaeoyxvgzxpn', N'Hummingbird', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'gcmetvwkiuunwis', N'ehgghyziprptdih', N'China', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'hrakgufaovhsvda', N'ehgghyziprptdih', N'Ireland', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'kqctiiwwraucivm', N'ehgghyziprptdih', N'Brazil', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'dfzgidybrwyniul', N'ehgghyziprptdih', N'Italy', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'trqlrnynqjjqrdx', N'bnxkbwdrcknioyy', N'Dooms', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'tqywurpvtgannjq', N'bnxkbwdrcknioyy', N'Dark', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'byyuqvopfbvfibo', N'bnxkbwdrcknioyy', N'Denmark', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'igehjqplekoczgd', N'bnxkbwdrcknioyy', N'Dunkirk', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'qqtxjghfzqkgtbx', N'sxfsqeimzzczaqr', N'Venus', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'tonzbepsjxnuguo', N'sxfsqeimzzczaqr', N'Saturn', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'fzbgqkrnnijemqt', N'sxfsqeimzzczaqr', N'Mercury', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'hetzztygbzkgrxv', N'sxfsqeimzzczaqr', N'Mars', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'gybjnkcbqqggwkc', N'gzqbgaxvpfajsqo', N'Herbert Hoover', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'pbhbgangllawxom', N'gzqbgaxvpfajsqo', N'Richard Nixon', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'ldqdczoghcakdij', N'gzqbgaxvpfajsqo', N'George W. Bush', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'egpovhtnvewvhyz', N'gzqbgaxvpfajsqo', N'Barack Obama', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'mnpfygzjfzblfnv', N'xamvxlifwuanrjl', N'Washington, D.C.', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'pjkhsenworpvcnn', N'xamvxlifwuanrjl', N'Boston', 0)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'bhrchwagruvsrot', N'xamvxlifwuanrjl', N'Philadelphia', 1)
INSERT [dbo].[Answer] ([aid], [qid], [content], [isTrue]) VALUES (N'jyzxmdfouzlsmjo', N'xamvxlifwuanrjl', N'Manhattan', 0)
GO
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'bnxkbwdrcknioyy', N'thinh', N'What does the “D” in “D-Day” stand for?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'ehgghyziprptdih', N'thinh', N'Which country held the 2016 Summer Olympics?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'gzqbgaxvpfajsqo', N'thinh', N'Who was the only U.S. President to resign?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'ipvykkpeexxhrjr', N'thinh', N'Which one of these characters is not friends with Harry Potter?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'sxfsqeimzzczaqr', N'thinh', N'Which planet is the hottest?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'thsohqeqfxycere', N'thinh', N'How many blue stripes are there on the U.S. flag?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'wnemuwobgxbjnlb', N'thinh', N'What was the name of the band Lionel Richie was a part of?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'xamvxlifwuanrjl', N'thinh', N'In which city can you find the Liberty Bell?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'xfzmhumnzozlwec', N'thinh', N'What is the name for the Jewish New Year?', CAST(N'2021-02-17' AS Date))
INSERT [dbo].[Question] ([qid], [userName], [content], [created]) VALUES (N'zgxyaeoyxvgzxpn', N'thinh', N'Which animal does not appear in the Chinese zodiac?', CAST(N'2021-02-17' AS Date))
GO
INSERT [dbo].[User] ([userName], [password], [email], [isTeacher]) VALUES (N'an', N'123', N'ng0cth1nh@gmail.com', 1)
INSERT [dbo].[User] ([userName], [password], [email], [isTeacher]) VALUES (N'duc', N'123', N'duc@gmail.com', 1)
INSERT [dbo].[User] ([userName], [password], [email], [isTeacher]) VALUES (N'hai', N'123', N'hai@gmail.com', 0)
INSERT [dbo].[User] ([userName], [password], [email], [isTeacher]) VALUES (N'thinh', N'123', N'thinh@gmail.com', 1)
INSERT [dbo].[User] ([userName], [password], [email], [isTeacher]) VALUES (N'tung', N'123', N'tung@gmail.com', 0)
GO
ALTER TABLE [dbo].[Answer]  WITH CHECK ADD  CONSTRAINT [FK_Answer_Question] FOREIGN KEY([qid])
REFERENCES [dbo].[Question] ([qid])
GO
ALTER TABLE [dbo].[Answer] CHECK CONSTRAINT [FK_Answer_Question]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_User] FOREIGN KEY([userName])
REFERENCES [dbo].[User] ([userName])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_User]
GO
