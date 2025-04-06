USE [tool_database]
GO

INSERT INTO [dbo].[tab] ([id], [icon], [level], [name], [url]) VALUES
    ('home', 'home', 1, 'Home', '/home'),
    ('file', 'folder_zip', 2, 'File tranfer', '/file'),
    ('accounts', 'lock', 3, 'Account Manager', '/accounts'),
    ('chat', 'chat', 4, 'In out', '/chat'),
    ('logout', 'logout', 5, 'Logout', '/logout');
GO


