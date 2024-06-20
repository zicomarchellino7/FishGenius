const { register, login, home, classes, profil, moduleContent, detail_kelas, forum, profilEdit, quizCheck, createForum, getForumMassage, sendMassage, classProgress, auth } = require("./handler");

const routes = [
    {
        method: 'GET',
        path: '/',
        handler: (request, h) => {
            return 'Server sudah berjalan';
        }
    },
    {
        method: 'POST',
        path: '/register',
        handler: register,
    },
    {
        method: 'POST',
        path: '/login',
        handler: login
    },
    {
        method: 'POST',
        path: '/home',
        handler: home,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/class',
        handler: classes,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/profile',
        handler: profil,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/module',
        handler: moduleContent,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'GET',
        path: '/module/{classid}',
        handler: detail_kelas,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'GET',
        path: '/module/{classid}/forum',
        handler: forum,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/editProfile',
        handler: profilEdit,
        options: {
            ext: {
                onPreAuth: { method: auth }
            },
            payload: {
                parse: true,
                multipart: {
                    output: 'stream'
                },
                maxBytes: 1000 * 1000 * 5 // 5 MB limit
            }
        },
    },
    {
        method: 'POST',
        path: '/quizCheck',
        handler: quizCheck,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/createForum',
        handler: createForum,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'GET',
        path: '/ForumMassage/{forumid}',
        handler: getForumMassage,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/sendMassage',
        handler: sendMassage,
        options: {
            ext: {
                onPreAuth: { method: auth }
            }
        },
    },
    {
        method: 'POST',
        path: '/classProgress',
        handler: classProgress,
        options: {
            ext: {
                onPreAuth: { method: auth }
            },
            payload: {
                parse: true,
                multipart: {
                    output: 'stream'
                },
                maxBytes: 1000 * 1000 * 5 // 5 MB limit
            }
        }
    },
];

module.exports = routes;
