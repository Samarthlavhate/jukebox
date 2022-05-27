create database jukebox;
use jukebox;
create table Genre (genreid int primary key auto_increment, genre varchar(50) not null);
create table artist(artistid int primary key auto_increment ,artist_name varchar(75) not null);
create table song(songid int primary key auto_increment , songname varchar(75) not null
, song_album varchar(75) , genreid int  ,artistid int  ,songduration varchar(10000),
foreign key(genreid) references genre(genreid) , foreign key(artistid) references artist(artistid));


alter table song  auto_increment = 20000;

describe song;

drop table song;
insert into genre(genre)values('rock');
insert into artist(artist_name) values('mein hi hu');
select*from genre;
select*from artist;
select *from song;
describe song;
insert into song(songname ,song_album,genreid, artistid, songduration) values('jimmy jimmy', 'album full of sad song',1 ,1,'2:20');

create view songlist as select songid,songname ,song_album,songduration, genre ,artist_name from song join genre on song.genreid = genre.genreid join artist on song.artistid= artist.artistid;
drop view songlist;
select *from songlist;

create table celeb(celebid int primary key auto_increment ,celebname varchar(75) not null);
create table podcast(episode int primary key auto_increment ,podcastname varchar(100), releasedate date default (curdate()), duration varchar(2000),celebid int ,foreign key(celebid) references celeb(celebid));
alter table podcast  auto_increment = 2000;
insert into celeb(celebname)values('karan hohar');
insert into podcast(podcastname,releasedate,duration,celebid)values('tautalks','2050-07-12','2:20:00',1);
set sql_safe_updates=0;
select *from podcast;
delete from podcast;
delete from celeb;
 
create view podcaste_view as select episode,podcastname ,releasedate,duration,celebname from podcast join celeb on podcast.celebid =celeb.celebid ;


select *from podcaste_view;
select *from songlist;
create table playlist(playlistid int primary key auto_increment,playlistname varchar(200) not null);
select*from playlist;

create table playlistContentTable(playlistcontentid int primary key auto_increment ,playlistid int,trackid int not null
,foreign key(playlistid) references playlist(playlistid));
select*from playlistcontenttable;
set sql_safe_updates=0;

select* from song;
select* from genre;
select* from artist;
select* from celeb;
select* from podcast;
select* from playlist;
select* from playlistContentTable;


delete from playlistcontenttable where trackid=2004;
delete from song where songid =20015;
delete  from podcast;
delete  from playlist where playlistid=16;
delete from playlistContentTable where playlistid =16;
drop table playlistcontenttable;
describe playlistcontenttable;
select trackid from playlistcontenttable where playlistid= (select playlistid from playlist where playlistname='motivation');
delete from playlist where playlistid=17;
delete from playlistcontenttable where playlistid=17;