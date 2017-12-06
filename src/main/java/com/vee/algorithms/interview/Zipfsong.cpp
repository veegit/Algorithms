#include<iostream>
#include <queue>

using namespace std;
//interview = spotify
struct Song {
	unsigned int id;
	string name;
	long long views;
	long long score;
};

class Zipfsong {
public:
	bool operator()(Song &s1, Song &s2)
	{
		if(s1.score > s2.score) return true;
		if(s1.score == s2.score && s1.id < s2.id) return true;
		return false;
	}
};

int main()
{
	priority_queue<Song,vector<Song>,Zipfsong> q;

	unsigned int n;
	int m;
	unsigned int id=0;
	string name;
	long long views, score;
	
	cin>>n;
	cin>>m;
	Song s[n];
	for(unsigned int j=0;j<n;j++) {
		cin>>s[j].views;
		string name;
		cin>>name;
		s[j].name = name;
		s[j].id = j+1;
		s[j].score = s[j].views*s[j].id;
		if(q.size() == m && q.top().score >= s[j].score)
			continue;
		q.push(s[j]);
		if(q.size() > m)
			q.pop();
	}
	m = q.size();
	Song s1[m];
	for(int i=0;i<m;i++)
	{
		s1[i] = q.top();
		q.pop();
	}
	for(int i=m-1;i>=0;i--)
	{
		cout<<s1[i].name<<endl;	
	}
	return 0;
}
		
