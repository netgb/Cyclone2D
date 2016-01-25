package pay.model.record;

import c2d.util.math.C2D_Array;
import c2d.util.misc.C2D_MiscUtil;

/**
 * ��Ϸ��¼������ ���ӷ������˻�õ�һ���ַ����������ͳ�һ����¼��
 * @author AndrewFan
 * 
 */
public class C2D_RecordSet
{
	private C2D_Array m_records=new C2D_Array();
	public C2D_RecordSet()
	{
	}
	/**
	 * ���¼��������һ����¼�����ӹ����л����ID��飬���������ͬ��ID��
	 * ����ʹ���¼�¼�������滻��֮ǰ����ͬID�ļ�¼�����������ݺ�������
	 * @param record
	 */
	public void addRecord(C2D_RecordItem record)
	{
		if(record==null)
		{
			return;
		}
		C2D_RecordItem sameIDRecord=null;
		for(int i=0;i<m_records.size();i++)
		{
			C2D_RecordItem rI =  (C2D_RecordItem)m_records.elementAt(i);
			if(rI.getRecordID()==record.getRecordID())
			{
				sameIDRecord=rI;
				break;
			}
		}
		if(sameIDRecord!=null)
		{
			sameIDRecord.setContent(record.getContent());
			sameIDRecord.setDescription(record.getDescription());
		}
		else
		{
			m_records.addElement(record);	
		}		
	}
	/**
	 * ����ID�����Ӧ�ļ�¼
	 * @param recordID ��¼ID
	 * @return ��¼
	 */
	public C2D_RecordItem getRecordByID(int recordID)
	{
		for(int i=0;i<m_records.size();i++)
		{
			C2D_RecordItem rI =  (C2D_RecordItem)m_records.elementAt(i);
			if(rI.getRecordID()==recordID)
			{
				return rI;
			}
		}
		return null;
	}
	/**
	 * �����±�id�����Ӧ�ļ�¼
	 * @param id ��¼�±�ID
	 * @return ��¼
	 */
	public C2D_RecordItem getRecord(int id)
	{
		if(id<0||id>=m_records.size())
		{
			return null;
		}
		C2D_RecordItem record =  (C2D_RecordItem)m_records.elementAt(id);
		return record;
	}
	/**
	 * �������������Ӧ�ļ�¼
	 * @param desc ��¼����
	 * @return ��¼
	 */
	public C2D_RecordItem getRecordByDesc(String desc)
	{
		for(int i=0;i<m_records.size();i++)
		{
			C2D_RecordItem rI =  (C2D_RecordItem)m_records.elementAt(i);
			if(rI.getDescription()==desc)
			{
				return rI;
			}
		}
		return null;
	}
	/**
	 * ����¼���ݴ��л������ת�����ַ������飬�Ա����ݸ�������
	 * @return ���л�������
	 */
	public String[] serializeOut()
	{
		String data[]=new String[m_records.size()];
		for(int i=0;i<m_records.size();i++)
		{
			C2D_RecordItem rI =  (C2D_RecordItem)m_records.elementAt(i);
			data[i]=rI.serializeOut();
		}
		return data;
	}
	/**
	 * ���ؼ�¼����
	 * @return ��¼����
	 */
	public int size()
	{
		return m_records.size();
	}
	/**
	 * ��ʾ��¼����
	 */
	public void logDetail()
	{
		C2D_MiscUtil.log("[Infor]","------����"+m_records.size()+"����¼-----");
		for(int i=0;i<m_records.size();i++)
		{
			C2D_RecordItem rI =  (C2D_RecordItem)m_records.elementAt(i);
			rI.logDetail();
		}
		C2D_MiscUtil.log("[Infor]","-------------------");
	}
}