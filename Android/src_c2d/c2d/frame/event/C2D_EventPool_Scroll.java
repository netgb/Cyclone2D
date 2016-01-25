package c2d.frame.event;

import c2d.frame.base.C2D_Widget;
import c2d.lang.math.C2D_Array;

public class C2D_EventPool_Scroll extends C2D_EventPool
{
	public C2D_EventPool_Scroll(C2D_Widget carrier)
	{
		super(carrier);
	}
	/**
	 * ��ϵͳ����ִ���¼���ִ��֮����Զ�ɾ��ִ����ϵ��¼�
	 * @param itemIndex ���������¼�����ĿID
	 * @param scroll true�������룬��������ø�����false����ʧȥ����
	 */
	public final void onCalled(int itemIndex,boolean scroll)
	{
		C2D_Array events = eventList;
		if(events!=null)
		{
			for(int i=0;i<events.size();i++)
			{
				C2D_EventMsg message = (C2D_EventMsg)events.elementAt(i);
				if(message!=null && message.m_event!=null)
				{
					C2D_Event_Scroll event = (C2D_Event_Scroll)message.m_event;
					if(event.doEvent(message.m_carrier,itemIndex,scroll))
					{
						events.removeElementAt(i);
						i--;
					}
				}

			}
		}
	}
	/**
	 * �����¼����������ظ�
	 * @param event �¼�
	 * @return �Ƿ�ɹ����ӣ�false��ʾ�����ظ���δ��ִ������
	 */
	public boolean add(C2D_Event_Scroll event)
	{
		return super.add(event);
	}
}