import java.util.LinkedList;
import java.util.Queue;

public class RR {
	//进程号、到达时间、运行时间、完成时间、周转时间、带权周转时间
	public int id;
	public double arriveTime,runTime,finishTime,taTime,wtaTime;
	//当前剩余所需运行时间
	public double remainingTime;
	
	//更新周转时间、带权周转时间
	public void re(double n){
		finishTime = n;
		taTime = finishTime-arriveTime;
		wtaTime = taTime/runTime;
	}
	public static void main(String[] args){
			//进入队列的任务序号、轮转时间、进程数
			int i = 0 , span = 1, n = 5;
			//当前时间
			double  nowTime;
			RR[] s = new RR[n];
			//初始化
			init(s,n);
			//就绪队列
			Queue<RR> q = new LinkedList<RR>();
			q.add(s[0]);
			i++;
			nowTime = s[0].arriveTime;
			while(!q.isEmpty()){
				//时间片大于该进程的服务时间
				if(span>=q.peek().remainingTime){
					nowTime += q.peek().remainingTime;
					//加入新的进程
					for(int j=i;j<n;j++){
						if(s[j].arriveTime<=nowTime){
							q.add(s[j]);
							i++;
						}
					}
					q.poll().re(nowTime);
					//System.out.println(nowTime);
					//防止还有剩余进程时进程中断
					if(i<n&&q.isEmpty()){
						q.add(s[i]);
						nowTime = q.peek().arriveTime;
						i++;
					}
				}
				else{
					nowTime += span;
					//更新服务剩余时间
					q.peek().remainingTime -= span;
					//加入新进程
					for(int j=i;j<n;j++){
						if(s[j].arriveTime<=nowTime){
							q.add(s[j]);
							i++;
						}
					}
					//送往就绪队列末尾
					q.add(q.poll());
				}	
			}
			System.out.println("进程号|到达时间|服务时间|完成时间|周转时间|带权周转时间");
			for(int j=0;j<n;j++){
				System.out.println(s[j].id+"\t"+s[j].arriveTime+"\t"+s[j].runTime+"\t"+s[j].finishTime+"\t"+s[j].taTime+"\t"+String.format("%.2f", s[j].wtaTime));
			}
			
		}
		public static void init(RR[] s,int n){
			for(int i=0;i<n;i++){
				s[i] = new RR();
				s[i].id = i;
			}
			s[0].arriveTime = 0; s[0].remainingTime = s[0].runTime = 4;
			s[1].arriveTime = 1; s[1].remainingTime = s[1].runTime = 3;
			s[2].arriveTime = 2; s[2].remainingTime = s[2].runTime = 4;
			s[3].arriveTime = 3; s[3].remainingTime = s[3].runTime = 2;
			s[4].arriveTime = 4; s[4].remainingTime = s[4].runTime = 4;
		}
}
